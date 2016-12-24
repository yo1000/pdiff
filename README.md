# PDiff

Compare PDFs.

## Usage

### Run from Java using jar

Compare files.

```
$ ls /dir/path/d1
A.pdf   B.pdf   C.pdf

$ java -jar pdiff.jar -f1 /dir/path/d1/A.pdf -f2 /dir/path/d1/B.pdf

f1: /dir/path/d1/A.pdf
f2: /dir/path/d1/B.pdf

@@ -1 +1 @@
..
```

Compare directories.

```
$ ls /dir/path/d1
A.pdf   B.pdf   C.pdf

$ ls /dir/path/d2
A.pdf   C.pdf

$ java -jar pdiff.jar -d1 /dir/path/d1 -d2 /dir/path/d2

f1: /dir/path/d1/A.pdf
f2: /dir/path/d2/A.pdf

@@ -1 +1 @@
..

f1: /dir/path/d1/C.pdf
f2: /dir/path/d2/C.pdf

@@ -1 +1 @@
..
```

### Run from Maven using sources

Compare files.

```
$ ls /dir/path/d1
A.pdf   B.pdf   C.pdf

$ ./mvnw clean spring-boot:run -U -Drun.arguments=-f1,/dir/path/d1/A.pdf,-f2,/dir/path/d1/B.pdf

f1: /dir/path/d1/A.pdf
f2: /dir/path/d1/B.pdf

@@ -1 +1 @@
..
```

Compare directories.

```
$ ls /dir/path/d1
A.pdf   B.pdf   C.pdf

$ ls /dir/path/d2
A.pdf   C.pdf

$ ./mvnw clean spring-boot:run -U -Drun.arguments=-d1,/dir/path/d1,-d2,/dir/path/d2

f1: /dir/path/d1/A.pdf
f2: /dir/path/d2/A.pdf

@@ -1 +1 @@
..

f1: /dir/path/d1/C.pdf
f2: /dir/path/d2/C.pdf

@@ -1 +1 @@
..
```

## Dependencies

### Apache Commons CLI

- http://commons.apache.org/proper/commons-cli/
- https://mvnrepository.com/artifact/commons-cli/commons-cli

### Apache PDFBox

- https://pdfbox.apache.org/
- https://mvnrepository.com/artifact/org.apache.pdfbox/pdfbox

### java-diff-utils

- https://code.google.com/archive/p/java-diff-utils/
- https://mvnrepository.com/artifact/com.googlecode.java-diff-utils/diffutils

