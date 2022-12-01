/**
 * A class implementing this interface provides independent helper methods for generating Huffman codes from strings as well as encoding and decoding strings using such Huffman codes.
 *
 * @see <a href="https://en.wikipedia.org/wiki/Huffman_coding">Huffman coding</a> at Wikipedia
 */
public interface IHuffmanTree {
	/**
	 * This method generates a linked list of <i>leaf nodes</i> from the given string {@code s}
	 * such that every character of the given string occurs exactly once wrapped in an {@link AbstractHuffmanNode}
	 * together with its {@link AbstractHuffmanNode#frequency} in the given string.
	 * All {@link AbstractHuffmanNode#character}s of the list returned by this method are pairwise distinct (different).
	 *
	 * @param s the string to be encoded using Huffman codes
	 * @return a linked list of leaf nodes - exactly one node for every unique character in the given string
	 */
	java.util.LinkedList<AbstractHuffmanNode> count(String s);

	/**
	 * This method merges the two smallest nodes K<sub>x</sub> and K<sub>y</sub> from the given list to form a new (inner) node K<sub>n</sub>.
	 * <p>The smallest node K<sub>x</sub> becomes the {@link AbstractHuffmanNode#zeroChild} and the next to smallest node K<sub>y</sub> becomes the {@link AbstractHuffmanNode#oneChild} of K<sub>n</sub>.</p>
	 * Afterwards, both nodes K<sub>x</sub> and K<sub>y</sub> are removed from the given list and the new node K<sub>n</sub> is added as the <i>first</i> element to the list.
	 * <p>In order to identify the two smallest nodes in the given list, this method first uses {@link java.util.Collections#sort(java.util.List)} to sort the nodes into ascending order
	 * such that afterwards K<sub>x</sub> will be the first and K<sub>y</sub> the second node in this list.</p>
	 * This method is usually called repeatedly from outside until there is only one node left in the list, which then will be the root of the Huffman tree.
	 *
	 * @param nodes the list of nodes from which to remove the two smallest nodes, merge them to a new node and replace them with this new node
	 */
	void mergeTwoNodes(java.util.LinkedList<AbstractHuffmanNode> nodes);

	/**
	 * This method generates a unique optimal prefix binary code for each character in the Huffman tree.
	 * <p>It traverses the tree recursively in a DFS manner from the given root to every leaf and collects the labels of each traversed edge to form the binary code (in a string for simplicity here).</p>
	 * If the method visits the {@link AbstractHuffmanNode#zeroChild} next, then it appends "0" to the accumulated code - otherwise it adds "1".
	 * <p>Whenever the DFS traversal hits a leaf node, it adds its {@link AbstractHuffmanNode#character} together with the collected code to the resulting map.</p>
	 *
	 * @param root the root of the Huffman tree
	 * @return a map that maps each character in the Huffman tree to a unique optimal prefix binary code
	 * @see <a href="https://en.wikipedia.org/wiki/Depth-first_search">DFS traversal</a>
	 */
	java.util.HashMap<Character, String> getEncoding(AbstractHuffmanNode root);

	/**
	 * This method encodes the given string {@code s} using the Huffman code collected from the given Huffman tree using {@link #getEncoding(AbstractHuffmanNode)}.
	 * <p>It processes the input string character by character and concatenates the corresponding binary codes for each character preserving their original order.</p>
	 *
	 * @param root the root of the Huffman tree for {@code s} usually generated using {@link #count(String)} and repeatedly calling {@link #mergeTwoNodes(java.util.LinkedList)} on its result
	 * @param s    the string to be encoded using Huffman codes
	 * @return a binary string containing the characters from {@code s} encoded using the given Huffman tree
	 * @throws IllegalArgumentException if the input string contains characters that cannot be encoded using the given Huffman tree
	 */
	String encode(AbstractHuffmanNode root, String s) throws IllegalArgumentException;

	/**
	 * This method decodes the given binary string {@code s} using the given Huffman tree.
	 * It represents the reverse operation to {@link #encode(AbstractHuffmanNode, String)}.
	 * <p>
	 * Because Huffman codes are prefix codes this method traverses the given Huffman tree following each binary character from the input string.
	 * When reaching a leaf of the Huffman tree, the used bits from the input are discarded and the {@link AbstractHuffmanNode#character} reached in the leaf is appended to the resulting decoded string.
	 * If the input is not yet empty, this methods starts a new traversal from the root using the remaining bits in the input string.
	 * </p>
	 * The decoding terminates successfully if processing the last bit of the input ends in a leaf of the given Huffman tree - otherwise the input is illegal.
	 *
	 * @param root the root of the Huffman tree used to generate {@code s}
	 * @param s    the binary string to be decoded using Huffman codes
	 * @return the string previously encoded using the given Huffman tree
	 * @throws IllegalArgumentException if the input string contains illegal characters (other than 0 or 1) or otherwise cannot be decoded using the given Huffman tree
	 * @see <a href="https://en.wikipedia.org/wiki/Prefix_code">prefix code</a> at Wikipedia
	 */
	String decode(AbstractHuffmanNode root, String s) throws IllegalArgumentException;
}