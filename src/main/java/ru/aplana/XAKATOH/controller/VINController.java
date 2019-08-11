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


        return VINGeneration.generateVIN("Europe", 2000);
    }
}
