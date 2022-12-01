import javax.swing.text.AbstractDocument;
import java.io.CharArrayReader;
import java.util.*;

public class HuffmanTree  implements IHuffmanTree{
    @Override
    public LinkedList<AbstractHuffmanNode> count(String s) {
        LinkedList<AbstractHuffmanNode> res=new LinkedList<>();
        ArrayList chrs=new ArrayList();
        int counter=0;
        for(int i=0;i<s.length();i++){
            if(chrs.contains(i)){
                continue;
            }
            for(int j=0;j<s.length();j++){
                if(s.charAt(i)==s.charAt(j)){
                    chrs.add(j);
                    counter++;
                }
            }
            HuffmanNode temp=new HuffmanNode(s.charAt(i),counter);
            res.add(temp);
            counter=0;
        }
        return res;


    }

    @Override
    public void mergeTwoNodes(LinkedList<AbstractHuffmanNode> nodes) {
        if(nodes.size()>=2) {
            Collections.sort(nodes);
            AbstractHuffmanNode temp = new HuffmanNode(nodes.get(0), nodes.get(1));
            nodes.remove(0);
            nodes.remove(0);
            nodes.add(0, temp);

        }


    }


    @Override
    public HashMap<Character, String> getEncoding(AbstractHuffmanNode root) {
        HashMap<Character,String > temp=new HashMap<>();
        getEncode(root,"",temp);
        return temp;

    }

    @Override
    public String encode(AbstractHuffmanNode root, String s) throws IllegalArgumentException {
        String res="";
        HashMap<Character, String> temp=getEncoding(root);
        for(int i=0;i<s.length();i++){
            if(temp.containsKey(s.charAt(i))){
                res+=temp.get(s.charAt(i));
            }
        }
        if(s!="" && res==""){
            throw new IllegalArgumentException();
        }
        return res;
    }

    @Override
    public String decode(AbstractHuffmanNode root, String s) throws IllegalArgumentException {

        String result="";
        result=decodeHelper(root,root,s,result,"");
        if(s!="" && result==""){
            throw new IllegalArgumentException();
        }
        return result;

    }

    private void getEncode(AbstractHuffmanNode root,String encode,HashMap<Character,String> temp) {
        if (root.zeroChild != null) {

            getEncode(root.zeroChild, encode + "0", temp);
        }
        if (root.oneChild != null) {

            getEncode(root.oneChild, encode + "1", temp);
        }
        if(root.zeroChild == null && root.oneChild == null) {
            if (root.character != '\0') {
                temp.put(root.character, encode);
            } else {
                throw new IllegalArgumentException();
            }
        }
        }

        private String decodeHelper(AbstractHuffmanNode original,AbstractHuffmanNode iterator,String s,String result,String temp){
            if (s.length() >=temp.length() && s!="") {
                if (iterator.zeroChild == null && iterator.oneChild == null) {
                    result += iterator.character;
                    s = s.substring(temp.length());
                    return decodeHelper(original, original, s, result, "");
                }
                if (s.length()>temp.length() && s.charAt(temp.length()) == '0' && iterator.zeroChild != null) {
                    return decodeHelper(original, iterator.zeroChild, s, result, temp + '0');
                }
                if (s.length()>temp.length() && s.charAt(temp.length()) == '1' && iterator.oneChild != null) {
                    return decodeHelper(original, iterator.oneChild, s, result, temp + '1');
                }
        }
            return result;

        }






    }

