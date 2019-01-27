package livelessons.xauth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.util.ReflectionUtils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;

abstract class TokenUtils {

	private static final String MAGIC_KEY = "obfuscate";

	private static final MessageDigest MESSAGE_DIGEST = buildDigest();

	private static MessageDigest buildDigest() {
		try {
			return MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e) {
			ReflectionUtils.rethrowRuntimeException(e);
		}
		return null;
	}

	static String createToken(UserDetails userDetails) {
		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		return userDetails.getUsername() + ":" + expires + ":"
				+ computeSignature(userDetails, expires);
	}

	private static String computeSignature(UserDetails userDetails, long expires) {
		String signature = "";
		signature += (userDetails.getUsername()) + (":");
		signature += (expires) + (":");
		signature += (userDetails.getPassword()) + (":");
		signature += (TokenUtils.MAGIC_KEY);
		return new String(Hex.encode(MESSAGE_DIGEST.digest(signature.getBytes())));
	}

	static String getUserNameFromToken(String authToken) {
		return Optional.ofNullable(authToken).map(at -> at.split(":")[0]).orElse(null);
	}

	static boolean validateToken(String authToken, UserDetails userDetails) {
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		String signature = parts[2];
		String signatureToMatch = computeSignature(userDetails, expires);
		return expires >= System.currentTimeMillis()
				&& signature.equals(signatureToMatch);
	}

}
