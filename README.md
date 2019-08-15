# JavaWare
### A Ransomware Built in Java

#### Disclaimer
> I built this purely for **educational purposes**. I take no responsibility and/or liability for how you choose to use any of the code available here. If executing the ransomware, please do not do so on your own machine as it will encrypt your files. Although I did include a function to decrypt, I am in no way responsible. 

JavaWare is a ransomware which will observe your system, learn which files you use, and then encrypt your machine. Once all files have been encrypted it will launch the payload screen. 

![alt text](https://raw.githubusercontent.com/kyralmozley/JavaWare/V1.0/Screenshots/Pay Load.png "Launch Screen")

### How does it learn which files I use?
>



java --module-path "C:\Users\Admin\Desktop\JavaWare\javafx-sdk-11.0.2\lib\" --add-modules=javafx.controls -jar Users\Admin\Desktop\JavaWare\JavaWare.jar


references: 
https://www.symantec.com/content/dam/symantec/docs/security-center/white-papers/increased-use-of-powershell-in-attacks-16-en.pdf
