package advicedeepdive;

import org.springframework.stereotype.Service;

@Service
public class SimpleService {

  public void doSomething() {
  }

  public void throwsRuntimeException() {
    throw new RuntimeException();
  }

  public String returnsStringAndThrowsRuntimeException() {
    throw new RuntimeException();
  }

  public void throwsException() throws Exception {
    throw new Exception();
  }

  public String returnsString() {
    return "42";
  }
  
  public int returnsInt() {
    return 42;
  }

  @Override
  public String toString() {
    return "SimpleService []";
  }

  public void aroundAdvice() {

  }

}
