package AutoFill;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class DLB {
    private ArrayList<String> suggestions = new ArrayList<String>();
    private StringBuilder sb = new StringBuilder();
    private DlbElement root = new DlbElement("");
    private File dictionary;
    public DLB(File dictionary)
    {
        this.dictionary = dictionary;
        BufferedReader br = null;
        FileReader fr = null;
        try
        {
            fr = new FileReader(dictionary);
            br = new BufferedReader(fr);
            String currentLine;
            String currChar;

            while ((currentLine = br.readLine()) != null) {
                incorporate(currentLine.toLowerCase());
            }

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {

                if (br != null)
                    br.close();

                if (fr != null)
                    fr.close();

            } catch (Exception ex) {

                ex.printStackTrace();

            }

        }

    }

    public DLB()
    {
        dictionary = null;
    }

    public void incorporate(String word)
    {
        try
        {
            diveAndAdd(root, word);
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }

    }
    public void diveAndAdd(DlbElement node, String word)
    {
        char firstChar;
        DlbElement elem;
        boolean keyFound = false;
        DlbElement keyFoundNode = null;
        if(word.length()>0)
        {
            firstChar = word.charAt(0);
            keyFound = false;
            keyFoundNode = null;
            if(!node.hasChildNodes())
            {
                elem = new DlbElement(Character.toString(firstChar));
                node.addchild(elem);
                elem.setParent(node);
                diveAndAdd(elem, word.substring(1));
            }
            else
            {
                for (DlbElement element: node.getChildren())
                {
                    if(element.getKey().equals(Character.toString(firstChar)))
                    {
                        keyFound = true;
                        keyFoundNode = element;
                        break;
                    }
                }
                if(keyFound)
                {
                    diveAndAdd(keyFoundNode, word.substring(1));
                }
                else
                {
                    elem = new DlbElement(Character.toString(firstChar));
                    node.addchild(elem);
                    elem.setParent(node);
                    diveAndAdd(elem, word.substring(1));
                }
            }
        }
        else
        {
            DlbElement elm = new DlbElement("^");
            node.addchild(elm);
            elm.setParent(node);
        }

    }
    public boolean search(DlbElement node, String key)
    {
        if(node.hasChildNodes())
        {
            for (DlbElement element: node.getChildren())
            {
                if(element.getKey().equals("^"))
                {
                    while(element.getParent()!=null)
                    {
                        element = element.getParent();
                        sb.append(element.getKey());
                    }
                    if(sb.reverse().equals(key));
                    {
                        return true;
                    }
                }
                else
                {
                    search(element, key);
                }
            }
        }
        return false;
    }
    public boolean contains(String key)
    {
        if(root.hasChildNodes())
        {
            for (DlbElement elm: root.getChildren())
            {
                if(search(elm, key))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public void printDive(DlbElement node)
    {
        if(node.hasChildNodes())
        {
            for (DlbElement element: node.getChildren())
            {
                if(element.getKey().equals("^"))
                {
                    while(element.getParent()!=null)
                    {
                        element = element.getParent();
                        sb.append(element.getKey());
                    }
                    System.out.println(sb.reverse());
                    sb = new StringBuilder();
                }
                else
                {
                    printDive(element);
                }
            }
        }
    }
    public void print()
    {
        if(root.hasChildNodes())
        {
            for (DlbElement node: root.getChildren())
            {
                    printDive(node);
            }
        }
    }
    public ArrayList<String> suggest(String currSequence)
    {
        suggestions.clear();
        char currChar = ' ';
        int foundCount = 0;
        DlbElement currNode = root;
        ArrayList<DlbElement> children;
        StringBuilder suggBuilder = new StringBuilder();

        for(int i = 0; i<currSequence.length(); i++)
        {
            currChar = currSequence.charAt(i);
            children = currNode.getChildren();
            for (DlbElement child:children)
            {
                if (child.getKey().charAt(0)==currChar)
                {
                    currNode = child;
                    foundCount++;
                }
            }
        }
        if(foundCount!=currSequence.length())
        {
            suggestions.clear();
            return suggestions;
        }
        findSuggestions(currNode, 0);
        return suggestions;
    }
    public void findSuggestions(DlbElement node, int suggCounter)
    {
        sb = new StringBuilder();
        if(node.hasChildNodes())
        {
            for (DlbElement element: node.getChildren())
            {
                if(element.getKey().equals("^"))
                {
                    while(element.getParent()!=null)
                    {
                        element = element.getParent();
                        sb.append(element.getKey());
                    }
                    suggestions.add(sb.reverse().toString());
                    suggCounter++;
                    sb = new StringBuilder();
                }
                else
                {
                        findSuggestions(element, suggCounter);
                }
            }
        }
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }
}

