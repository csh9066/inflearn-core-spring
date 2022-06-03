package hello.corespring.scope;

import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.assertThat;

public class PrototypeTest {

    @Test
    void protoTypeBeanFind() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);
        System.out.println("find PrototypeBean1");
        ProtoTypeBean bean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("find PrototypeBean2");
        ProtoTypeBean bean2 = ac.getBean(ProtoTypeBean.class);
        assertThat(bean1).isNotSameAs(bean2);
        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean {

        @PostConstruct
        public void init() {
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        public void destroy() {
            System.out.println("ProtoTypeBean.destroy");
        }
    }
}
