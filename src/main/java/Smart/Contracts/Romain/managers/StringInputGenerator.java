package Smart.Contracts.Romain.managers;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Random;

public class StringInputGenerator extends InputGenerator{

    private int string_len = 1;
    private static final char[] CHARS_ALLOWED = "abcdefghijklmnopqrstuvwxyz0123456789".toCharArray();

    public StringInputGenerator(){
        super();
    }
    public StringInputGenerator(int string_len){
        super();
        this.string_len = string_len;
    }

    public String generate(){
        char[] tab = CHARS_ALLOWED;
        StringBuilder stringBuilder = new StringBuilder(string_len);
        Random random = new Random();
        for(int i = 0; i < string_len; i++){
            char c = tab[random.nextInt(tab.length)];
            stringBuilder.append(c);
        }
        return stringBuilder.toString();
    }

    public int getString_len(){return this.string_len;}
    public void setString_len(int length){this.string_len = length;}
}
