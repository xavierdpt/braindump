package utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class ClassToJSON {
    public static String toJSONString(Object o) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        ArrayNode top = om.createArrayNode();
        ObjectNode x = om.createObjectNode();
        x.set("name", TextNode.valueOf(o.getClass().getName()));
        addInterfaces(om, x, o.getClass());
        top.add(x);
        Class<?> sup = o.getClass().getSuperclass();
        while (sup != null) {
            ObjectNode u = om.createObjectNode();
            u.set("name", TextNode.valueOf(sup.getName()));
            addInterfaces(om, u, sup);
            top.add(u);
            sup = sup.getSuperclass();
        }
        return om.writerWithDefaultPrettyPrinter().writeValueAsString(top);

    }

    private static void addInterfaces(ObjectMapper om, ObjectNode node, Class<?> clazz) {
        ArrayNode interfaces = om.createArrayNode();
        for (Class<?> i : clazz.getInterfaces()) {
            interfaces.add(TextNode.valueOf(i.getClass().getName()));
        }
        node.set("interfaces", interfaces);
    }

}
