# LookMergeAndErase</br>

</br>
</br>
The task was the next:</br>
Create a program that picked from customer the path to some folder.</br>
This folder has to contain files. After that, user has to enter the word that will look for at those files.</br>
The look for process must starts in first thread.</br>
If word was found contains of files (that contain word) should be copied to consolidated file.</br>
This copy process should also pass in first thread.</br>
After the first thread is finished, begin to work second thread.</br>
It has to remove from consolidated file content the words that lie in the user file.</br>
Im Main method I have to show statistics about execution result of those threads.</br>
</br>
</br>
Program operating conditions.</br>
1. Path to operate folder has to be absolut, example D:\Projects\Java\LookMergeAndErase\src\main\resources.</br>
2. Folder has exist, otherwise program will stop to work.</br>
3. Bad words have to lie in bad_words.txt file, directly in folder (path according point 1).</br>
4. Bad words can be divided into file with space, dot, comma, line separator, tabulator symbol(\t).</br>
5. Inside operate folder must be several files with text inside.</br>
6. Program reads files in *.txt, *.dat, *.dll formats. It is checked by author.</br>
7. Be careful! Consolidated file will have the name results.txt. Don't name your files with the same name.</br>
8. result.txt file replaces each build automatically.</br>
</br>
</br>
Test of program.</br>
1. Input incorrect absolut path. Example D:\Projects\Java\/LookMergeAndErase\src\main\resources.</br>
Program will show you an error. Incorrect path.</br>
2. Input incorrect absolut path.</br>
Example D:\Projects\Java\LookMergeAndErase\src\main\resources\folder\ or even D:\Projects\Java\LookMergeAndErase\src\main\resources\sample.txt.</br>
Program will show you an error. Directory not exist.</br>
3. Input correct absolut path YourPath\LookMergeAndErase\src\main\resources. Example D:\Projects\Java\LookMergeAndErase\src\main\resources.</br>
Program catch it. Path correct.</br>
4. Enter search word - lock. None of the files contain this word.</br>
Program will show you the result, that result.txt is empty.</br>
5. Enter search word - tutorial.</br>
3 files from 5'th will consolidate into one file result.txt.</br>
Program will show you the result before removing bad words and after removing bad words.</br>
You can check file result.txt. It no contains bad words.
