package ttl.larku.tricky;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author whynot
 */
interface Trick {
    public void doTrick();
}

@Component
//@Primary
//@Profile("us-east")
//@Order(2)
@Qualifier("us-west")
class Trick1 implements Trick {
    @Override
    public void doTrick() {
        System.out.println("Handstand");
    }
}

@Component
//@Primary
//@Profile("us-east")
//@Order(2)
@Qualifier("us-west")
class Trick3 implements Trick {
    @Override
    public void doTrick() {
        System.out.println("Card Trick");
    }
}

@Component
//@Profile("us-west")
//@Order(1)
@Qualifier("us-east")
class Trick2 implements Trick {
    @Override
    public void doTrick() {
        System.out.println("Cart Wheel");
    }
}

@Component
class Circus
{
//    @Autowired
    @Resource(name = "trick2")
    private Trick trick;


    @Autowired
    @Qualifier("us-west")
    private List<Trick> allWestTricks;

    @Autowired
    @Qualifier("us-east")
    private List<Trick> allEastTricks;

    public void startShow() {
//        trick.doTrick();
        allWestTricks.forEach(Trick::doTrick);
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("us-east");
        context.scan("ttl.larku.tricky");
        context.refresh();

        Circus c = context.getBean("circus", Circus.class);
        c.startShow();
    }
}
