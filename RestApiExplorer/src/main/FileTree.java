package main;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;

// URI path "/filetree"
@Path("/filetree")
public class FileTree {

    //function provide html head with css
    @Produces("text/plain")
    private String getHTMLHead(){
        return "<html>\n" +
                "\n" +
                "\t<head>\n" +
                "\t\t<title>File Tree</title>\n" +
                "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html;charset=utf-8\" />\n" +
                "\t\t\n" +
                "\t\t<style type=\"text/css\">\n" +
                "\t\t\tBODY,\n" +
                "\t\t\tHTML {\n" +
                "\t\t\t\tpadding: 0px;\n" +
                "\t\t\t\tmargin: 0px;\n" +
                "\t\t\t}\n" +
                "\t\t\tBODY {\n" +
                "\t\t\t\tfont-family: Verdana, Arial, Helvetica, sans-serif;\n" +
                "\t\t\t\tfont-size: 11px;\n" +
                "\t\t\t\tbackground: #EEE;\n" +
                "\t\t\t\tpadding: 15px;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\tH1 {\n" +
                "\t\t\t\tfont-family: Georgia, serif;\n" +
                "\t\t\t\tfont-size: 20px;\n" +
                "\t\t\t\tfont-weight: normal;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\tH2 {\n" +
                "\t\t\t\tfont-family: Georgia, serif;\n" +
                "\t\t\t\tfont-size: 16px;\n" +
                "\t\t\t\tfont-weight: normal;\n" +
                "\t\t\t\tmargin: 0px 0px 10px 0px;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\t.example {\n" +
                "\t\t\t\tfloat: left;\n" +
                "\t\t\t\tmargin: 15px;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "\t\t\t.demo {\n" +
                "\t\t\t\twidth: 200px;\n" +
                "\t\t\t\theight: 400px;\n" +
                "\t\t\t\tborder-top: solid 1px #BBB;\n" +
                "\t\t\t\tborder-left: solid 1px #BBB;\n" +
                "\t\t\t\tborder-bottom: solid 1px #FFF;\n" +
                "\t\t\t\tborder-right: solid 1px #FFF;\n" +
                "\t\t\t\tbackground: #FFF;\n" +
                "\t\t\t\toverflow: scroll;\n" +
                "\t\t\t\tpadding: 5px;\n" +
                "\t\t\t}\n" +
                "\t\t\t\n" +
                "UL.jqueryFileTree {\n" +
                "\tfont-family: Verdana, sans-serif;\n" +
                "\tfont-size: 11px;\n" +
                "\tline-height: 18px;\n" +
                "\tpadding: 0px;\n" +
                "\tmargin: 0px;\n" +
                "}\n" +
                "\n" +
                "UL.jqueryFileTree LI {\n" +
                "\tlist-style: none;\n" +
                "\tpadding: 0px;\n" +
                "\tpadding-left: 20px;\n" +
                "\tmargin: 0px;\n" +
                "\twhite-space: nowrap;\n" +
                "}\n" +
                "\n" +
                "UL.jqueryFileTree A {\n" +
                "\tcolor: #333;\n" +
                "\ttext-decoration: none;\n" +
                "\tdisplay: block;\n" +
                "\tpadding: 0px 2px;\n" +
                "}\n" +
                "\n" +
                "UL.jqueryFileTree A:hover {\n" +
                "\tbackground: #BDF;\n" +
                "}\n" +
                "\t\t</style>\n" +
                "\t\t<link href=\"F:/idea_proj/RestApiExplorer/js/jquery.fileTree-1.01/jqueryFileTree.css\" rel=\"stylesheet\" type=\"text/css\" media=\"screen\" />"+
                "\t\t\t<div class=\"example\">\n" +
                "\t\t\t<h2>Default options</h2>\n" +
                "\t\t\t<div id=\"fileTreeDemo_1\" class=\"demo\">";
    }

    //generate html page with file tree
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String getClichedMessage() {
        String out = getHTMLHead();
        //get server directory
        String dir = "../docroot/";
        //if dir is null then return null html page
        if (dir == null) {
            return null;
        }

        //in case if path contains '\\' or doesn't contain '/'
        if (dir.charAt(dir.length()-1) == '\\') {
            dir = dir.substring(0, dir.length()-1) + "/";
        } else if (dir.charAt(dir.length()-1) != '/') {
            dir += "/";
        }

        //if directory exists then build tree
        //directory make with class name 'directory collapsed'
        //files make with class name 'file ext_' + file type
        if (new File(dir).exists()) {
            String[] files = new File(dir).list(new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.charAt(0) != '.';
                }
            });
            Arrays.sort(files, String.CASE_INSENSITIVE_ORDER);
            out+="\n<ul class=\"jqueryFileTree\">";
            // All dirs
            for (String file : files) {
                if (new File(dir, file).isDirectory()) {
                    out+="\n\t\t<li class=\"directory collapsed\"><a href=\"#\" rel=\"" + dir + file + "/\">"
                            + file + "</a></li>";
                }
            }
            // All files
            for (String file : files) {
                if (!new File(dir, file).isDirectory()) {
                    int dotIndex = file.lastIndexOf('.');
                    String ext = dotIndex > 0 ? file.substring(dotIndex + 1) : "";
                    out+="\n\t\t<li class=\"file ext_" + ext + "\"><a href=\"#\" rel=\"" + dir + file + "\">"
                            + file + "</a></li>";
                }
            }
            out+="\n</ul>";
        }
        return out + "\n</div>\n" +
                "\t\t</div></body></html>";
    }
}