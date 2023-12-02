## Description
LockIT is a secure note-taking that uses AES 256 encryption ensures that your notes remain secure and viewable only by you​. 
This is accomplished with the Crypto package to encrypt and decrypt notes that the user creates.

## Motivation
The app is a way secure note-taking for government workers to have a straightforward, secure system for recording and storing their notes.​

## Code Example
        public static String encrypt(String input, String password) {
        try {
            SecretKey key = generateAESKey(password);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            byte[] iv = new byte[AES_IV_SIZE];
            new SecureRandom().nextBytes(iv);
            IvParameterSpec ivSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.ENCRYPT_MODE, key, ivSpec);
            byte[] encrypted = cipher.doFinal(input.getBytes());
            byte[] ciphertext = new byte[AES_IV_SIZE + encrypted.length];
            System.arraycopy(iv, 0, ciphertext, 0, AES_IV_SIZE);
            System.arraycopy(encrypted, 0, ciphertext, AES_IV_SIZE, encrypted.length);
            return Base64.getEncoder().encodeToString(ciphertext);
        } catch (Exception e){
            return "ERROR";
        }
    }

## Contributors
- Albert Villarreal
- William Chaltas
- Aidan Kollar
- Mason Bourque

## Known Issues
- 

## Instructions To Run
1. In Android Studio select File>New>Project from Version Control!
   
![Navigating to create a new project from version control](https://cdn.discordapp.com/attachments/1148731657197727766/1179629438435065896/image.png?ex=657a7a86&is=65680586&hm=1430ea0933f7bbcfdab4f98b468184449749507362e01e598a922fe0c405e841&)

2. Paste "https://github.com/UTSA-CS-3443/LockIT.git" into the URL portion and press Clone.

![Steps to cloning the project into Android Studio](https://cdn.discordapp.com/attachments/1148731657197727766/1179629438745456650/image.png?ex=657a7a86&is=65680586&hm=88e24008f23d07b83c03f82f43e5a2a32be466ab60b08a7237db0c0c1a590b10&)

3. Click on the Run button for the app in the top right or use the Shift+F10 shortcut.
 
![Location of the run botton at top right of screen](https://cdn.discordapp.com/attachments/1148731657197727766/1179630228545470514/image.png?ex=657a7b42&is=65680642&hm=7f4e0d296a706bade99551a09cc5170f8a3f3a26405343b6d03820bfce2ba185&)

Voila, you should now see the app on your emulator (To enter the use the password LockIt123)

![LockIT app viewed from the emulator screen](https://cdn.discordapp.com/attachments/1087261300859604993/1180301702419722342/image.png?ex=657cec9e&is=656a779e&hm=3a76096a256c0ba2a1b51dbc419e51dfc003824f30dd36c9e3c70a0118c8ebda&)


## How to Use LockIT

Once logged in using the password above, you will see your notes and options on the bottom of the screen. From here, you can add a note, favorite it, or even search for a note title or contents.

![Note home page](https://cdn.discordapp.com/attachments/1087261300859604993/1180302996198920303/image.png?ex=657cedd3&is=656a78d3&hm=f79e6b469919566931ee786b5d3cc5af2c4d2256f816c5cea023c951e531dbc1&)

To add a note, press on the plus icon in the bottom of the notes home page and you will be able to create a title, content and see what your note looks like when encrypted. To save press the save icon next to the title.

![Note creation feature](https://cdn.discordapp.com/attachments/1087261300859604993/1180302915932528792/image.png?ex=657cedc0&is=656a78c0&hm=cf6905d8271ef84219105de63de598807669f0bc1227522c09e6f4a828f11eeb&)

Going back to the notes home page and clicking on the search icon will pop up a search bar where you can search for notes which have the title of or contain content of your search.

![Search feature](https://cdn.discordapp.com/attachments/1087261300859604993/1180303074800193567/image.png?ex=657cede6&is=656a78e6&hm=1657b3cb48dca4cb15a46f147e3ee304874de24bc99766fe2719c7112a0f247f&)
