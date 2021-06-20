package ejbs;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.ejb.Stateful;

@Stateful
public class MyEJB implements MyEJBI {

    ObjectMapper om = new ObjectMapper();

    @Override
    public String getMessage() {
        ObjectNode node = om.createObjectNode();
        node.set("message", TextNode.valueOf("Hello world!"));
        try {
            return om.writerWithDefaultPrettyPrinter().writeValueAsString(node);
        } catch (JsonProcessingException e) {
            return e.getMessage();
        }
    }

    @Remove
    void thisMethodShouldRemoveTheBean() {
        // usually makes sense only for stateful beans
    }

    @PostConstruct
    void postConstruct() {
        System.out.println("[" + this.getClass().getName() + "] Post construct");
    }
}
