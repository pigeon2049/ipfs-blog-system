package eth.moecat.ocean.controller;

import io.ipfs.api.IPFS;
import io.ipfs.api.MerkleNode;
import io.ipfs.api.NamedStreamable;
import io.ipfs.multihash.Multihash;
import org.apache.commons.text.StringEscapeUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostBlog {

    @Autowired
    private IPFS ipfs;

    @PostMapping
    public boolean postBlog(@RequestBody String body) throws IOException {
        body= StringEscapeUtils.escapeJava(body);
        body=body.substring(2,body.length() - 2);
        VelocityEngine velocityEngine = new VelocityEngine();
        velocityEngine.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        velocityEngine.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());
        velocityEngine.init();

        VelocityContext context = new VelocityContext();
        context.put("blogContent", body);

        Template template = velocityEngine.getTemplate("templates/template.vue");
        StringWriter writer = new StringWriter();
        template.merge(context, writer);


        List<MerkleNode> added = ipfs.add(new NamedStreamable
                .ByteArrayWrapper(System.currentTimeMillis()+".html",writer.toString().getBytes(StandardCharsets.UTF_8)));
        added.forEach(item->{
            Multihash hash = item.hash;
            try {
                ipfs.pin.add(hash);
                ipfs.files.mkdir("/posts",true);
                ipfs.files.cp("/ipfs/"+hash.toString(),"/posts/"+item.name.orElse(System.currentTimeMillis()+".html"),false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        return true;

    }

}
