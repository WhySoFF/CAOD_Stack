public class Main {

    static ModifiedSimmetrTree tree = new ModifiedSimmetrTree();

    public static void main(String[] args) {
        tree.add(4);
        tree.add(-1);
        tree.add(-4);
        tree.add(1);
        tree.add(3);
        tree.add(-6);
        tree.add(8);
        tree.add(9);
        tree.modifiedTree();
        tree.delete(-1);
        tree.delete(9);
        tree.addToMod(10);
        tree.simmTraversalModified();
    }
}