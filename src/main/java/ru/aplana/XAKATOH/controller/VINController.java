package ru.aplana.XAKATOH.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.aplana.XAKATOH.VINGeneration;

import java.security.SecureRandom;
import java.util.Locale;
import java.util.Objects;
import java.util.Random;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class VINController extends BaseController {

    private static final String upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static final String lower = upper.toLowerCase(Locale.ROOT);

    private static final String digits = "0123456789";

    public String randomString(int length) {
        Random random;
        char[] symbols;
        char[] buf;

        random = Objects.requireNonNull(new SecureRandom());
        symbols = lower.toCharArray();
        buf = new char[length];

        buf[0] = upper.toCharArray()[random.nextInt(upper.length())];

        for (int idx = 1; idx < buf.length; ++idx)
            buf[idx] = symbols[random.nextInt(symbols.length)];
        return new String(buf);
    }

    @RequestMapping(value = "/getVIN", method = {GET, POST})
    @ResponseBody
    public String getVIN(){
        Random random = new Random();
        return "<style>" +
                "a{" +
                "text-align:center;" +
                "display:inline-block;" +
                "padding:0.3em 1.2em;" +
                "margin:0 0.1em 0.1em 0;" +
                "border:0.16em solid rgba(255,255,255,0);" +
                "border-radius:1em;" +
                "box-sizing: border-box;" +
                "text-decoration:none;" +
                "font-family:'Roboto',sans-serif;" +
                "font-weight:300;" +
                "font-size:40pt;" +
                "color:#000000;" +
                "text-shadow: 0 0.04em 0.04em rgba(0,0,0,0.35);" +
                "text-align:center;" +
                "align:middle;" +
                "transition: all 0.2s;" +
                "}" +
                "</style>" +
                "<a style=\"background-color:" +
                String.format("#%06x", random.nextInt(256*256*256)) +
                "\">" +
                VINGeneration.generateVIN()+
                "</a>";
    }
}
