import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//This is the input and output that allows the code acess info it needs from outside 
class Main {
    public static void main(String[] args) {
        (new Main()).init();
    }

    void print(Object o) {
        System.out.println(o);
    }

    void printt(Object o) {
        System.out.print(o);
    }

    void init() {
        // Read the original txt
        String file = Input.readFile("Original.txt");

        // Encode1 (Caesar cipher +7)
        String encodedMsg1 = caesarCipher(file, 7);
        Input.writeFile("Encode1.txt", encodedMsg1);

        // Encode2(Atbash cipher)
        String encodedMsg2 = atbashCipher(encodedMsg1);
        Input.writeFile("Encode2.txt", encodedMsg2);

        // Encode3 (ASCII shift -1)
        String encodedMsg3 = asciiShift(encodedMsg2, -1);
        Input.writeFile("Encode3.txt", encodedMsg3);

        // Decoding the Encode text
        String file2 = Input.readFile("Encode3.txt");

        // Decode1 (ASCII shift +1)
        String decodedMsg1 = asciiShift(file2, 1);
        Input.writeFile("Decode1.txt", decodedMsg1);

        // Decode2 (Atbash cipher)
        String decodedMsg2 = atbashCipher(decodedMsg1);
        Input.writeFile("Decode2.txt", decodedMsg2);

        // Decode3 (Caesar cipher -7)
        String decodedMsg3 = caesarCipher(decodedMsg2, -7);
        Input.writeFile("Decode3.txt", decodedMsg3);
    }

    // Atbash cipher this is the atbash cipher 
    String atbashCipher(String txt) {
        StringBuilder build = new StringBuilder();
//String Bulider we used this to have  changeable string that can be modfiy without creating a new object
        for (int x = 0; x < txt.length(); x++) {
            char ch = txt.charAt(x);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) (base + ('Z' - ch));
            }
			//Append adds This addtional data to this and  is used add data to the end extsting StringBulider object.
            build.append(ch);
        }
           //This returns bulid to the string 
        return build.toString();
    }

    // ASCII Shift -1
    String asciiShift(String txt, int shift) {
        StringBuilder build = new StringBuilder();
//This other string bulider which makes the code less messy since it can be modfiy without creating other object
        for (int x = 0; x < txt.length(); x++) {
            char ch = txt.charAt(x);
            ch = (char) (ch + shift); // Shift the ASCII value
			//we used append to add data
            build.append(ch);
        }
   
        return build.toString();
    }

    // Caesar Cipher +7 
    String caesarCipher(String txt, int shift) {
      //This is other instance of us using stringBulider
	  StringBuilder build = new StringBuilder();
        for (int x = 0; x < txt.length(); x++) {
            char ch = txt.charAt(x);
            if (Character.isLetter(ch)) {
                char base = Character.isLowerCase(ch) ? 'a' : 'A';
                ch = (char) (base + (ch - base + shift + 26) % 26);
            }
			//We used append for this one to add adtional data
            build.append(ch);
        }
        return build.toString();
    }
}

// Read and write files
//this alows us the read the files 
class Input {
    static String readFile(String filename) {
        try {
            return new String(Files.readAllBytes(Paths.get(filename)));
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return "";
        }
    }
//This allows us the writes files 
    static void writeFile(String filename, String content) {
        try {
            Files.write(Paths.get(filename), content.getBytes());
        } catch (IOException e) {
            System.err.println("Error writing file: " + e.getMessage());
        }
    }
}

//Names:Yianni,John,Amoya






