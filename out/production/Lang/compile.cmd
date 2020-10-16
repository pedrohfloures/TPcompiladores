cd C:\Users\guilherme.libardi\Desktop\Lang
java -jar lang\parser\JFlex.jar -nobak lang\parser\Lang.flex
java -jar lang\parser\beaver-cc-0.9.11.jar -T lang\parser\Lang.grammar
javac -cp .;beaver-rt-0.9.11.jar lang\LangCompiler.java