Add-Type -AssemblyName System.IO.Compression.FileSystem
function Unzip {
    param([string]$zipfile, [string]$outpath)
    [System.IO.Compression.ZipFile]::ExtractToDirectory($zipfile, $outpath)
}

$jar_source = "https://github.com/kyralmozley/JavaWare/raw/V1.0/JavaWare.jar"
$jar_destination = "c:\temp\JavaWare.jar"
$client = New-Object System.Net.WebClient
$client.DownloadFile($jar_source, $jar_destination)

If(!(Test-Path -Path "C:\temp\javafx-sdk-11.0.2" -PathType Container)) {
#install java fx if not already
    $jfx_source = "https://www.dropbox.com/sh/vxoxawnr9cn1tq4/AAB0JWg41z7G0-72ALbOIulca?dl=1"
    $jfx_zip = "c:\temp\javafx-sdk-11.0.2.zip"
    $client.DownloadFile($jfx_source, $jfx_zip)

    $javafx = "c:\temp\javafx-sdk-11.0.2"
    $jfx_srczip = "c:\temp\javafx-sdk-11.0.2\lib\src.zip"

    #unzip
    Expand-Archive $jfx_zip -DestinationPath $javafx
    Expand-Archive $jfx_srczip -DestinationPath "c:\temp\javafx-sdk-11.0.2\lib\src"
    Remove-Item $jfx_zip
    Remove-Item $jfx_srczip
}

#get system java version
$JAVA_VERSION = Get-ChildItem -Path "C:\Program Files\Java" | Select-String -Pattern 'jdk-'

#launchhhhhh
$java = "C:\Program Files\Java\" + $JAVA_VERSION + "\bin\java.exe"
cd ..\..\..\..\..\..\
Start-Process -FilePath $java -ArgumentList '--module-path "C:\temp\javafx-sdk-11.0.2\lib" --add-modules=javafx.controls -jar temp\JavaWare.jar' -NoNewWindow
