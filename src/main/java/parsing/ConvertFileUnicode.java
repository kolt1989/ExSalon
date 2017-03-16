package parsing;

import java.io.*;

/**
 * Created by user on 03.03.2017.
 */
public class ConvertFileUnicode {

    public void convert (String infile, String outfile, String from, String to)
            throws IOException, UnsupportedEncodingException
    {
        InputStream in;
        if(infile != null)
            in=new FileInputStream(infile);
        else
            in=System.in;
        OutputStream out;
        if(outfile != null)
            out=new FileOutputStream(outfile);
        else
            out=System.out;

        if(from == null) from=System.getProperty("file.encoding");
        if(to == null) to=System.getProperty("file.encoding");


        Reader r=new BufferedReader(new InputStreamReader(in, from));
        Writer w=new BufferedWriter(new OutputStreamWriter(out, to));


        char[] buffer=new char[4096];
        int len;
        while((len=r.read(buffer)) != -1)
            w.write(buffer, 0, len);
        r.close();
        w.flush();
        w.close();
    }

}
