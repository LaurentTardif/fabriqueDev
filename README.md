# fdev

# Installation

**Important:** Because GitHub's maximum file size is 100M, the data archive had to be splited into smaller chunks. Before runnning this project, you need to merge them back toghether. To do so, run: 

````bash
cd archive
cat archive.parta* > archive.tar.gz
mv data ..
cd ..
rm -fr archive # optional
````

If you make changes to the data folder and want to upload them, you can create your own splitted version by running the following :

````bash
rm -fr archive
mkdir archive
GZIP=-9 tar -zcvf archive.tar.gz data
split -b 80M archive.tar.gz "archive/archive.part" # On macOS, you may need to use 80m with a lowecase 'M'
rm archive.tar.gz
````
