package com.learning.linnyk.lmi;


import com.learning.linnyk.lmi.components.MessageService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class LmiMain {

    /**
     * Lookup Method Injection работает за счет объявления в одиночном бине метода поиска,
     * который возвращает экземпляр неодиночного бина.
     * При получении ссылки на одиночный бин внутри приложения в действительности получается
     * ссылка на динамически созданный подкласс, в котором Spring реализует метод поиска.
     * Типовая реализация включает определение метода поиска как абстрактного.
     *
     * Для того что бы вызывыть прототип в сингелтоне нужно в сингелтоне классе
     * объявить метод который бы возвращал тип прототипа, а когда создается
     * бин сингелтона в параметр lookup-method в name - имя метода
     * bean - бин прототипа
     *
     * Пример:
     * <lookup-method name="getMessage" bean="message"/>
     *
     * И еще нужно библиотека cglib для кода генерации
     *
     */
    public static void main(String[] args) {

        final ApplicationContext context = new ClassPathXmlApplicationContext("/lmi/spring-config.xml");
        final MessageService messageService = context.getBean(MessageService.class);

        messageService.send();
        messageService.send();
        messageService.send();
    }
}
