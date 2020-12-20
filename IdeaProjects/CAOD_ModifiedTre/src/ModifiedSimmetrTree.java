import java.util.ArrayList;


public class ModifiedSimmetrTree {

    private Node root = null;
    private Node HEAD;

    ModifiedSimmetrTree() {
        HEAD = new Node(root, HEAD);
    }

    void add(int key) {
        if(root == null) {
            root = new Node(key);
            root.rightChild = HEAD;
        } else {
            pushElem(key, root);
        }
    }

    void addToMod(int key) {
        pushToMod(key, root);
    }

    private void pushToMod(int key, Node current) {
        if(key < current.key) {
            if(current.leftChild == null) {
                current.leftChild = new Node(key, current);
            }
            else
                pushToMod(key, current.leftChild);
        } else if (key > current.key) {
            if (current.rightChild == HEAD) {
                current.rFlag = true;
                current.rightChild = new Node(key, HEAD);
                current.rightChild.rFlag = true;
            } else if(!current.rFlag) {
                current.rFlag = true;
                Node temp = current.rightChild;
                current.rightChild = new Node(key, temp);
            } else {
                pushToMod(key, current.rightChild);
            }
        }
    }

    private void pushElem(int key, Node current) {
        if(key < current.key) {
            if(current.leftChild == null) {
                current.leftChild = new Node(key);
            }
            else
                pushElem(key, current.leftChild);
        } else if (key > current.key) {
            if(current.rightChild == null) {
                current.rightChild = new Node(key);
            } else if (current.rightChild == HEAD) {
                current.rightChild = new Node(key);
                current.rightChild.rightChild = HEAD;
            }
            else
                pushElem(key, current.rightChild);
        }
    }

    private Node search(int key, Node current) {
        if(key == current.key) {
            return current;
        } else if(key < current.key) {
            if(current.leftChild == null)
                return null;
            else
                return search(key, current.leftChild);
        } else {
            if(current.rightChild == null || current.rightChild == HEAD)
                return null;
            else
                return search(key, current.rightChild);
        }
    }

    private void rightSew(Node current) {
        if(ptr != null) {
            if(ptr.rightChild == null) {
                ptr.rFlag = false;
                ptr.rightChild = current;
            }
        }
        ptr = current;
    }

    private void recursiveModified(Node node) {
        if(node != null) {
            recursiveModified(node.leftChild);
            rightSew(node);
            recursiveModified(node.rightChild);
        }
    }

    Node ptr = HEAD;

    void modifiedTree() {
        recursiveModified(root);
    }


    boolean contains(int key) {
        return search(key, root) != null;
    }

    private void modTraversal(Node current) {
        while(current != HEAD) {
            while (current.leftChild != null) {
                current = current.leftChild;
            }
            System.out.printf("%d ", current.key);
            while (!current.rFlag) {
                current = current.rightChild;
                if(current == HEAD)
                    break;
                System.out.printf("%d ", current.key);
            }
            current = current.rightChild;
        }

    }

    void simmTraversalModified() {
        System.out.println();
        modTraversal(root);
    }

    private int searchReplace(Node current) {
        int key = current.key;
        while(current.rightChild != null && current.rFlag) {
            current = current.rightChild;
            key = current.key;
        }
        return key;
    }

    private Node deleteNode(int key, Node current) {
        if(key == current.key) {
            if(current.leftChild == null) {
                if(current.key == current.rightChild.key)
                    return null;
                else
                    return current.rightChild;
            }
            int replaceKey = searchReplace(current.leftChild);
            current.key = replaceKey;
            current.leftChild =  deleteNode(replaceKey, current.leftChild);
            return current;
        } else if(key < current.key) {
            current.leftChild = deleteNode(key, current.leftChild);
            return current;
        }
        current.rightChild = deleteNode(key, current.rightChild);
        return current;
    }

    private Node recursive(Node current) {
        if(!arr.contains(current.key)) {
            arr.add(current.key);
            if (current.leftChild != null) {
                return recursive(current.leftChild);
            }
        }
        if(current.rightChild == HEAD)
            return null;
        if(current.rFlag && arr.contains(current.rightChild.key)) {
            current.rFlag = false;
        } else {
            return recursive(current.rightChild);
        }
        return null;
    }


    ArrayList<Integer> arr;

    private void finalModifiedTree() {
        arr = new ArrayList<>();
        recursive(root);
    }

    void delete(int key) {
        if(contains(key)) {
            root = deleteNode(key, root);
            finalModifiedTree();
        }
    }


    class Node {
        Node leftChild;
        Node rightChild;
        boolean rFlag;
        int key;

        Node(int key) {
            this.key = key;
            leftChild = null;
            rightChild = null;
            rFlag = true;
        }

        Node(int key, Node next) {
            this.key = key;
            leftChild = null;
            rightChild = next;
            rFlag = false;
        }

        Node(Node  l, Node r) {
            leftChild = l;
            rightChild = r;
            rFlag = true;
        }
    }
}