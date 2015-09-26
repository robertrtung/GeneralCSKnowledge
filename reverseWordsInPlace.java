public class Reverser {

	private char [] words;

	public Reverser(char [] wordArray){
		words = wordArray;
	}

	public static void main(String [] args){
		char [] wordArray = {'a', ' ', 'b', 'c', ' ', ' ', 'd'};
		Reverser r = new Reverser(wordArray);
		r.reverseWordsInPlace();
		System.out.println(r.printToString);

	}

	/*
	* reverses the words (i.e. groups of letters separated by spaces) in a char array in place 
	* 
	*/
	private void reverseWordsInPlace (){
		//reverses all the letters in the array
		for (int i = 0; i < words.length/2; i++){
			char tmp = words[i];
			words[i] = words[words.length - 1 - i];
			words[words.length - 1 - i] = tmp;
		}
		//find the spaces in the reversed array

		boolean inWord = false;
		for (int i = 0; i < words.length; i++){
			int startIndex, endIndex;
			if (!inWord && words[i] != ' '){
				startIndex = i;
				inWord = true;
			}
			else if (inWord && words[i] == ' '){
				endIndex = i-1;
				inWord = false;
				reverseWord(startIndex, endIndex);
			}

			else if (inWord && i == words.length-1){
				endIndex = i;
				reverseWord(startIndex, endIndex);
			}
		}
	}


	/*
	* reverses a single word in place
	* 
	* @param startIndex start index of the word
	* @param endIndex end index of the word
	*/
	private void reverseWord (int startIndex, int endIndex){
		for (int i = startIndex; i < (startIndex + endIndex) / 2; i++){
			char tmp = words[i];
			words[i] = words[endIndex - (i - startIndex)];
			words[endIndex - (i - startIndex)] = tmp;
		}
	}

	public String printToString(){
		return new String(words);
	}

}