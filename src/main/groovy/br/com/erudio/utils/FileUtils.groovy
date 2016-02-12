package br.com.erudio.utils

class FileUtils {

	def copyFile(String filePath, String fileDestinationPath){
		def fileSourceStream = new File(filePath).newDataInputStream()
		def fileDestinationStream = new File(fileDestinationPath).newDataOutputStream()
		fileDestinationStream << fileSourceStream
		fileSourceStream.close()
		fileDestinationStream.close()
	}
	
	def printFile(String filePath){
		new FileInputStream( new File(filePath) ).eachLine { println it }
	}
	
	def String fileToString(String filePath){
		def fileSourceStream = new File(filePath).newDataInputStream();
		fileSourceStream.getText();
		fileSourceStream.close()
	}
	
	
}
