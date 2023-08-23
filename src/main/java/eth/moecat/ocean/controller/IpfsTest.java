package eth.moecat.ocean.controller;


import com.google.common.io.ByteStreams;
import io.ipfs.api.*;
import io.ipfs.multihash.Multihash;
import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/hi")
public class IpfsTest {

    @Autowired
    private IPFS ipfs;

    private final String helloWorldId="QmamqRMjCqeZyLE8XnCnnxL3TSSuGM82fx58t2tS71YKLF";

    @GetMapping
    public void ipfsTest(HttpServletResponse response) throws IOException {
        Multihash filePointer = Multihash.fromBase58(helloWorldId);
        ServletOutputStream outputStream = response.getOutputStream();

        ByteStreams.copy(ipfs.catStream(filePointer), outputStream);
        outputStream.close();
    }

}
