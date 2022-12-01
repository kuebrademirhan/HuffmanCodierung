import static org.junit.Assert.*;
import java.lang.reflect.*;
import java.util.*;
import org.junit.*;

public class HuffmanPublicTest {
	// ========== SYSTEM ==========

	// ========== TEST-DATA ==========
	private static final Random RND = new Random(4711_0815_666L);
	private static final String stringOriginal = "Algorithms and Datastructures are really nice! \u263A";
	private static final String stringEncoded = "111001001111110011001101110110000111101110010101010001010111111111100111110010000010101000001100101110100000100111101101010001001111011000111101010001100111100001001011110110111011101111000100110001";
	private static final int stringOriginalDisjointLen = 21;

	// ========== CHECK INTESTINES ==========
	@Test(timeout = 666)
	public void pubTest__HuffmanNode__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = HuffmanNode.class;
		assertTrue(clazz + " must be public!", Modifier.isPublic(clazz.getModifiers()));
		assertFalse(clazz + " must not be abstract!", Modifier.isAbstract(clazz.getModifiers()));
		assertFalse(clazz + " must not be an annotation!", clazz.isAnnotation());
		assertFalse(clazz + " must not be an enumeration!", clazz.isEnum());
		assertFalse(clazz + " must not be an interface!", clazz.isInterface());
		assertSame(clazz + " must extend a certain super-class!", AbstractHuffmanNode.class, clazz.getSuperclass());
		assertEquals(clazz + " must implement a certain number of interfaces!", 0, clazz.getInterfaces().length);
		assertEquals(clazz + " must declare a certain number of inner annotations!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " must declare a certain number of inner classes!", 0, getDeclaredClasses(clazz).length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " must declare a certain number of fields!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz + " must declare a certain number of constructors (possibly including default constructor)!", 2, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue(constructor + " - constructor must be public!", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(constructor + " - constructor must have a certain number of parameters!", 2, constructor.getParameterTypes().length);
			assertTrue(constructor + " - constructor must have certain parameter types!", (constructor.getParameterTypes()[0] == Character.class && constructor.getParameterTypes()[1] == long.class) || (constructor.getParameterTypes()[0] == AbstractHuffmanNode.class && constructor.getParameterTypes()[1] == AbstractHuffmanNode.class));
		}
		Method[] methods = getDeclaredMethods(clazz);
		assertEquals(clazz + " must declare a certain number of methods!", 1, methods.length);
		for (Method method : methods) {
			assertTrue(method + " - method must be public!", Modifier.isPublic(method.getModifiers()));
			assertFalse(method + " - method must not be static!", Modifier.isStatic(method.getModifiers()));
			assertEquals(method + " - method must have a certain name!", "compareTo", method.getName());
			assertEquals(method + " - method must have a certain number of parameters!", 1, method.getParameterTypes().length);
			assertSame(method + " - method must have certain parameter types!", AbstractHuffmanNode.class, method.getParameterTypes()[0]);
		}
	}

	@Test(timeout = 666)
	public void pubTest__HuffmanTree__Intestines__IF_THIS_VERY_IMPORTANT_TEST_FAILS_THEN_YOU_WILL_GET_NO_POINTS_AT_ALL() {
		Class<?> clazz = HuffmanTree.class;
		assertTrue(clazz + " must be public!", Modifier.isPublic(clazz.getModifiers()));
		assertFalse(clazz + " must not be abstract!", Modifier.isAbstract(clazz.getModifiers()));
		assertFalse(clazz + " must not be an annotation!", clazz.isAnnotation());
		assertFalse(clazz + " must not be an enumeration!", clazz.isEnum());
		assertFalse(clazz + " must not be an interface!", clazz.isInterface());
		assertSame(clazz + " must extend a certain super-class!", Object.class, clazz.getSuperclass());
		assertEquals(clazz + " must implement a certain number of interfaces!", 1, clazz.getInterfaces().length);
		assertSame(clazz + " must implement certain interfaces!", IHuffmanTree.class, clazz.getInterfaces()[0]);
		assertEquals(clazz + " must declare a certain number of inner annotations!", 0, clazz.getDeclaredAnnotations().length);
		assertEquals(clazz + " must declare a certain number of inner classes!", 0, getDeclaredClasses(clazz).length);
		Field[] fields = getDeclaredFields(clazz);
		assertEquals(clazz + " must declare a certain number of fields!", 0, fields.length);
		Constructor<?>[] constructors = getDeclaredConstructors(clazz);
		assertEquals(clazz + " must declare a certain number of constructors (possibly including default constructor)!", 1, constructors.length);
		for (Constructor<?> constructor : constructors) {
			assertTrue(constructor + " - constructor must be public!", Modifier.isPublic(constructor.getModifiers()));
			assertEquals(constructor + " - constructor must have a certain number of parameters!", 0, constructor.getParameterTypes().length);
		}
		Method[] methods = getDeclaredMethods(clazz);
		assertTrue(clazz + " must declare a least number of methods!", getDeclaredMethods(IHuffmanTree.class).length <= methods.length);
		for (Method method : methods) {
			boolean isExpected = false;
			for (Method abstractMethod : getDeclaredMethods(IHuffmanTree.class)) {
				if (method.getName().equals(abstractMethod.getName()) && method.getParameterTypes().length == abstractMethod.getParameterTypes().length) {
					boolean typesMatch = true;
					for (int typeIdx = 0; typeIdx < method.getParameterTypes().length; typeIdx++) {
						if (method.getParameterTypes()[typeIdx] != abstractMethod.getParameterTypes()[typeIdx]) {
							typesMatch = false;
							break;
						}
					}
					if (typesMatch) {
						isExpected = true;
						break;
					}
				}
			}
			if (isExpected) {
				assertTrue(method + " - method must be public!", Modifier.isPublic(method.getModifiers()));
				assertFalse(method + " - method must not be static!", Modifier.isStatic(method.getModifiers()));
			} else {
				assertTrue(method + " - method must be private!", Modifier.isPrivate(method.getModifiers()));
			}
			assertFalse(method + " - method must not be abstract!", Modifier.isAbstract(method.getModifiers()));
		}
	}

	// ========== PUBLIC TEST: HuffmanNode ==========
	@Test(timeout = 666)
	public void pubTest__HuffmanNode__cons__compareTo() {
		for (int pass = 0; pass < 42; pass++) {
			int freq1 = RND.nextInt(4711), freq2 = freq1 + 1 + RND.nextInt(4711);
			Character char1 = (char) RND.nextInt(128), char2 = (char) (char1 + 1 + RND.nextInt(128));
			// ---------- leafs ----------
			AbstractHuffmanNode nodeLeaf11 = new HuffmanNode(char1, freq1);
			AbstractHuffmanNode nodeLeaf12 = new HuffmanNode(char1, freq2);
			AbstractHuffmanNode nodeLeaf22 = new HuffmanNode(char2, freq2);
			AbstractHuffmanNode nodeLeaf11duplicate = new HuffmanNode(char1, freq1);
			Comparable<AbstractHuffmanNode> nodeLeaf11comparableOnly = new HuffmanNode(char1, freq1);
			// ---------- cons
			assertEquals(char1, nodeLeaf11.character);
			assertEquals(freq1, nodeLeaf11.frequency);
			assertNull(nodeLeaf11.zeroChild);
			assertNull(nodeLeaf11.oneChild);
			// ---------- compareTo
			assertTrue(nodeLeaf11.compareTo(nodeLeaf12) < 0);
			assertTrue(nodeLeaf11.compareTo(nodeLeaf22) < 0);
			assertTrue(nodeLeaf12.compareTo(nodeLeaf22) < 0);
			assertEquals(0, nodeLeaf11.compareTo(nodeLeaf11duplicate));
			assertEquals(0, nodeLeaf11duplicate.compareTo(nodeLeaf11));
			for (AbstractHuffmanNode[] nodes : new AbstractHuffmanNode[][]{{nodeLeaf11, nodeLeaf11}, {nodeLeaf12, nodeLeaf12}, {nodeLeaf22, nodeLeaf22}}) {
				assertEquals(0, nodes[0].compareTo(nodes[1]));
			}
			assertEquals(0, nodeLeaf11comparableOnly.compareTo(nodeLeaf11));
			assertTrue(nodeLeaf12.compareTo(nodeLeaf11) > 0);
			assertTrue(nodeLeaf22.compareTo(nodeLeaf11) > 0);
			assertTrue(nodeLeaf22.compareTo(nodeLeaf12) > 0);
			// ---------- inner nodes ----------
			AbstractHuffmanNode nodeInner = new HuffmanNode(nodeLeaf11, nodeLeaf22);
			AbstractHuffmanNode nodeInnerDuplicate = new HuffmanNode(nodeLeaf11, nodeLeaf22);
			// ---------- cons
			assertEquals(Character.valueOf((char) 0), nodeInner.character);
			assertEquals(freq1 + freq2, nodeInner.frequency);
			assertSame(nodeLeaf11, nodeInner.zeroChild);
			assertSame(nodeLeaf22, nodeInner.oneChild);
			// ---------- compareTo
			assertTrue(nodeLeaf11.compareTo(nodeInner) < 0);
			assertTrue(nodeLeaf22.compareTo(nodeInner) < 0);
			assertEquals(0, nodeInner.compareTo(nodeInnerDuplicate));
			assertEquals(0, nodeInnerDuplicate.compareTo(nodeInner));
			assertTrue(nodeInner.compareTo(nodeLeaf11) > 0);
			assertTrue(nodeInner.compareTo(nodeLeaf22) > 0);
		}
	}

	// ========== PUBLIC TEST: HuffmanTree ==========
	@Test(timeout = 666)
	public void pubTest__HuffmanTree__count() {
		IHuffmanTree huffmanTree = new HuffmanTree();
		LinkedList<AbstractHuffmanNode> nodes = huffmanTree.count(stringOriginal), nodesSorted;
		assertNotNull(nodes);
		// --------------------
		nodesSorted = new LinkedList<>(nodes);
		Collections.sort(nodesSorted);
		assertEquals(stringOriginalDisjointLen, nodesSorted.size());
		assertEquals(Character.valueOf('!'), nodesSorted.getFirst().character);
		assertEquals(1, nodesSorted.getFirst().frequency);
		assertEquals(Character.valueOf(' '), nodesSorted.getLast().character);
		assertEquals(6, nodesSorted.getLast().frequency);
	}

	@Test(timeout = 666)
	public void pubTest__HuffmanTree__merge() {
		IHuffmanTree huffmanTree = new HuffmanTree();
		LinkedList<AbstractHuffmanNode> nodes = huffmanTree.count(stringOriginal), nodesSorted;
		printNodes(nodes);
		// --------------------
		huffmanTree.mergeTwoNodes(nodes);
		printNodes(nodes);
		assertEquals(stringOriginalDisjointLen - 1, nodes.size());
		nodesSorted = new LinkedList<>(nodes);
		Collections.sort(nodesSorted);
		for (AbstractHuffmanNode node : nodesSorted) {
			assertNotEquals(Character.valueOf('!'), node.character);
			assertNotEquals(Character.valueOf('A'), node.character);
			if (node == nodes.getFirst()) {
				assertEquals(Character.valueOf((char) 0), node.character);
				assertEquals(2, node.frequency);
				assertNotNull(node.zeroChild);
				assertEquals(Character.valueOf('!'), node.zeroChild.character);
				assertNotNull(node.oneChild);
				assertEquals(Character.valueOf('A'), node.oneChild.character);
			} else {
				assertNotEquals(Character.valueOf((char) 0), node.character);
			}
		}
		// --------------------
		huffmanTree.mergeTwoNodes(nodes);
		printNodes(nodes);
		assertEquals(stringOriginalDisjointLen - 2, nodes.size());
		Collections.sort(nodesSorted = new LinkedList<>(nodes));
		for (AbstractHuffmanNode node : nodesSorted) {
			assertNotEquals(Character.valueOf('!'), node.character);
			assertNotEquals(Character.valueOf('A'), node.character);
			assertNotEquals(Character.valueOf('D'), node.character);
			assertNotEquals(Character.valueOf('d'), node.character);
		}
		// --------------------
		while (nodes.size() > 1) {
			huffmanTree.mergeTwoNodes(nodes);
		}
		printNodes(nodes);
		AbstractHuffmanNode root = nodes.getFirst();
		assertEquals(stringOriginal.length(), root.frequency);
	}

	@Test//(timeout = 666)
	public void pubTest__HuffmanTree__encode_decode() {
		IHuffmanTree huffmanTree = new HuffmanTree();
		LinkedList<AbstractHuffmanNode> nodes = huffmanTree.count(stringOriginal);
		assertNotNull(nodes);
		while (nodes.size() > 1) {
			huffmanTree.mergeTwoNodes(nodes);
		}
		AbstractHuffmanNode root = nodes.getFirst();
		// --------------------
		HashMap<Character, String> encoding = huffmanTree.getEncoding(root);
		assertNotNull(encoding);
		assertEquals("111001", encoding.get('A'));
		assertEquals("0010", encoding.get('u'));
		assertEquals("111110", encoding.get('D'));
		assertEquals("100", encoding.get(' '));
		assertEquals("110001", encoding.get('\u263A'));
		// --------------------
		String encoded = huffmanTree.encode(root, stringOriginal);
		assertEquals(stringEncoded, encoded);
		// --------------------
		String decoded = huffmanTree.decode(root, stringEncoded);
		assertEquals(stringOriginal, decoded);
		// --------------------
		System.out.println("Required bits without vs. with compression: " + 16 * stringOriginal.length() + " vs. " + stringEncoded.length() + " (deflated to " + Math.round(stringEncoded.length() * 10000d / (16 * stringOriginal.length())) / 100d + "%)");
	}

	// ========== HELPER ==========
	private static void printNodes(LinkedList<AbstractHuffmanNode> nodes) {
		assertNotNull(nodes);
		assertFalse(nodes.isEmpty());
		for (AbstractHuffmanNode node : nodes) {
			System.out.printf("%1$s%2$2dx'%3$c'%4$s", (node == nodes.getFirst() ? "" : "|"), node.frequency, (node.character == 0 ? '\u2588' : node.character), (node == nodes.getLast() ? "\n" : ""));
		}
	}

	// ========== HELPER: Intestines ==========
	// @AuD-STUDENT: DO NOT USE REFLECTION IN YOUR OWN SUBMISSION!
	private static Class<?>[] getDeclaredClasses(Class<?> clazz) {
		java.util.List<Class<?>> declaredClasses = new java.util.ArrayList<>();
		for (Class<?> c : clazz.getDeclaredClasses()) {
			if (!c.isSynthetic()) {
				declaredClasses.add(c);
			}
		}
		return declaredClasses.toArray(new Class[0]);
	}

	private static Field[] getDeclaredFields(Class<?> clazz) {
		java.util.List<Field> declaredFields = new java.util.ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			if (!f.isSynthetic()) {
				declaredFields.add(f);
			}
		}
		return declaredFields.toArray(new Field[0]);
	}

	private static Constructor<?>[] getDeclaredConstructors(Class<?> clazz) {
		java.util.List<Constructor<?>> declaredConstructors = new java.util.ArrayList<>();
		for (Constructor<?> c : clazz.getDeclaredConstructors()) {
			if (!c.isSynthetic()) {
				declaredConstructors.add(c);
			}
		}
		return declaredConstructors.toArray(new Constructor[0]);
	}

	private static Method[] getDeclaredMethods(Class<?> clazz) {
		java.util.List<Method> declaredMethods = new java.util.ArrayList<>();
		for (Method m : clazz.getDeclaredMethods()) {
			if (!m.isBridge() && !m.isSynthetic()) {
				declaredMethods.add(m);
			}
		}
		return declaredMethods.toArray(new Method[0]);
	}
}