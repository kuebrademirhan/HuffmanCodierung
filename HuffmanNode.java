public class HuffmanNode extends AbstractHuffmanNode {


    public HuffmanNode(Character char1, long freq1) {
      super(char1,freq1,null,null); // zerochild?
    }

    public HuffmanNode(AbstractHuffmanNode nodeLeaf11, AbstractHuffmanNode nodeLeaf22) {
        super('\0',nodeLeaf11.frequency+nodeLeaf22.frequency,nodeLeaf11,nodeLeaf22);
    }

    @Override
    public int compareTo(AbstractHuffmanNode that) {
        if(this.frequency<that.frequency){
            return -1;
        }else if(this.frequency==that.frequency){
            if(this.character.compareTo(that.character)<0){
                return -1;
            }else if(this.character.compareTo(that.character)>0){
                return 1;
            }else{
                return 0;
            }
        }else{
            return 1;
        }

    }
}
