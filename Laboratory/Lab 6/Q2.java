import java.util.ArrayList;

class Document {
    private final String from;
    private final String to;
    private final String content;

    public Document (String from, String to, String content) {
        this.from = from;
        this.to = to;
        this.content = content;
    }

    String getFrom() {
        return from;
    }
    String getTo() {
        return to;
    }
    String getContent() {
        return content;
    }
}

interface DocumentProcess { boolean process(Document doc); }

record UserCheck(ArrayList<String> validUsers) implements DocumentProcess {

    @Override
    public boolean process(Document doc) {
        boolean v = this.validUsers.contains(doc.getTo()) && this.validUsers.contains(doc.getFrom());
        System.out.println(v ? "From/To valid." : "Invalid sender or receiver. Stop process.");
        return v;
    }
}

record ContentCheck(ArrayList<String> badWords) implements DocumentProcess {

    @Override
    public boolean process(Document doc) {
        String[] words = doc.getContent().toLowerCase().split("\\W+");

        for (String word : words) {
            for (String badWord : badWords) {
                if (word.startsWith(badWord)) {
                    System.out.printf("Found bad word: %s.%n", badWord);
                    return false;
                }
            }
        }


        System.out.println("Content is clean.");
        return false;
    }
}

public class Q2 {
    static void main(String[] args) {
        final ArrayList<String> validUsers = new ArrayList<>(){{
            add("Ken");
            add("Tom");
            add("June");
        }};

        final ArrayList<String> badWords = new ArrayList<>(){{
            add("stupid");
            add("idiot");
        }};

        final ArrayList<DocumentProcess> processes = new ArrayList<>(){{
            add(new UserCheck(validUsers));
            add((new ContentCheck(badWords)));
        }};

        Document doc1 = new Document("Ken", "Tom", "Hello Tom, you are great!");
        Document doc2 = new Document("June", "david", "Hey David, stupid guy!");

        System.out.println("\nProcessing doc1:");
        for (DocumentProcess process : processes) if (!process.process(doc1)) break;

        System.out.println("\nProcessing doc2:");
        for (DocumentProcess process : processes) if (!process.process(doc2)) break;
    }
}