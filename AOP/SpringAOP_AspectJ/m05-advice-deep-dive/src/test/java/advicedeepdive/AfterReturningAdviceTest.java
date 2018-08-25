package advicedeepdive;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import configuration.AdviceDeepDiveConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AdviceDeepDiveConfiguration.class)
public class AfterReturningAdviceTest {

  @Autowired
  AfterReturningAdvice afterReturningAdvice;

  @Autowired
  SimpleService simpleService;

  @Before
  public void reset() {
    afterReturningAdvice.reset();
  }

  @Test
  public void afterReturningIsNotCalledIfReturnTypeDoesntMatch() {
    assertFalse(afterReturningAdvice.isAfterReturningCalled());
    simpleService.returnsInt();
    assertFalse(afterReturningAdvice.isAfterReturningCalled());
  }

  @Test(expected = RuntimeException.class)
  public void afterReturningIsNotCalledIfExceptionIsThrown() {
    assertFalse(afterReturningAdvice.isAfterReturningCalled());
    try {
      simpleService.returnsStringAndThrowsRuntimeException();
    } finally {
      assertFalse(afterReturningAdvice.isAfterReturningCalled());
    }
  }

  @Test
  public void afterReturningIsCalledIfReturnTypeMatches() {
    assertFalse(afterReturningAdvice.isAfterReturningCalled());
    simpleService.returnsString();
    assertTrue(afterReturningAdvice.isAfterReturningCalled());
  }

}
