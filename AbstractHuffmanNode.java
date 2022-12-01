/**
 * Subclasses of this class represent nodes of a so-called Huffman tree used as an intermediate representation towards creating an appropriate Huffman encoding.
 * <p>
 * If this node is a leaf, then it holds a {@link #character} together with the number of its occurrences ({@link #frequency}) in the text to be encoded and both {@link #zeroChild} and {@link #oneChild} are {@code null}.
 * </p>
 * It this is a non-leaf node, then the {@link #character} is the <i>null terminator</i> and the {@link #frequency} is the sum of all {@link #frequency} values from all leafs of the tree rooted at this node (in this case both children are <i>not</i> {@code null}).
 *
 * @see <a href="https://en.wikipedia.org/wiki/Huffman_coding">Huffman coding</a> at Wikipedia
 * @see <a href="https://en.wikipedia.org/wiki/Null_character">null terminator</a> at Wikipedia
 */
public abstract class AbstractHuffmanNode implements Comparable<AbstractHuffmanNode> {
	/**
	 * If this is a leaf node then this variable contains a character from the text to be encoded.
	 * If this is a non-leaf (inner) node then this variable is the <i>null terminator</i> and has no specific meaning.
	 */
	public final Character character;
	/**
	 * If this is a leaf node then this variable represents the number of occurrences of {@link #character} in the text to be encoded.
	 * If this is a non-leaf (inner) node then this variable is the sum of all corresponding values from all leafs of the tree rooted at this node.
	 */
	public final long frequency;
	/**
	 * The "left" child node or subtree: The edge connecting to it is labelled "0".
	 */
	public final AbstractHuffmanNode zeroChild;
	/**
	 * The "right" child node or subtree: The edge connecting to it is labelled "1".
	 */
	public final AbstractHuffmanNode oneChild;

	public AbstractHuffmanNode(Character character, long frequency, AbstractHuffmanNode zeroChild, AbstractHuffmanNode oneChild) {
		this.character = character;
		this.frequency = frequency;
		this.zeroChild = zeroChild;
		this.oneChild = oneChild;
	}

	/**
	 * Compares this node with the specified node for order. Returns a negative integer, zero, or a positive integer as this node is less than, equal to, or greater than the specified node.
	 * <ul>
	 * <li>Both nodes this and that are first compared according to their {@link #frequency} (the node with smaller {@link #frequency} is smaller).</li>
	 * <li>In case that both nodes this and that have equal {@link #frequency}, then they are compared lexicographically according to their {@link #character} (the node with smaller {@link #character} is smaller)</li>
	 * <li>If both nodes have equal {@link #frequency} and equal {@link #character}, then they are considered equal as well.</li>
	 * </ul>
	 *
	 * @param that the node to be compared
	 * @return a negative integer, zero, or a positive integer as this node is less than, equal to, or greater than the specified node
	 */
	@Override
	public abstract int compareTo(AbstractHuffmanNode that);
}