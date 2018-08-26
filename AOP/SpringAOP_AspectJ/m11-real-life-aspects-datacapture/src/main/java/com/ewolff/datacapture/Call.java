package com.ewolff.datacapture;

import org.aspectj.lang.JoinPoint;

class Call {
	private int depth;
	private String signatureAndParameters;
	private String result;

	public Call(JoinPoint joinPoint, int depth) {
		this.depth = depth;
		this.signatureAndParameters = formatSignatureAndParameters(joinPoint);
	}

	private String formatSignatureAndParameters(JoinPoint joinPoint) {
		StringBuilder builder = new StringBuilder();
		builder.append(joinPoint.getStaticPart().getSignature().toString());
		Object[] args = joinPoint.getArgs();
		if (args.length > 0) {
			builder.append(" args ");
			for (Object arg : args) {
				builder.append(arg.toString());
				builder.append(" ");
			}
		}
		return builder.toString();
	}

	public int getDepth() {
		return depth;
	}

	public String getSignatureAndParameters() {
		return signatureAndParameters;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	@Override
	public String toString() {
		return signatureAndParameters + (result == null ? "" : " result "+result);
	}


}