package com.iw.rjava;

import org.rosuda.REngine.REXP;
import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.REngineException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;


public class rJavaCon {
	public RConnection rConnection = null;
	public REXP rexp = null;
	public String retStr = "";
	public rJavaCon() throws RserveException {
		this.rConnection = new RConnection();
	}
		
	public byte[] returnRImg() throws REngineException, REXPMismatchException{
		String device = "jpeg";
		rexp = rConnection.parseAndEval("try(" + device + "('test2.jpg', quality=90))");
	
		rConnection.parseAndEval("stock <- read.csv(\"D:/workspace/R/Project/stock.txt\", header=FALSE, sep=\";\", stringsAsFactors=FALSE)");
		rConnection.parseAndEval("attach(stock)");
		rConnection.parseAndEval("gsub(',','',stock$V2)");
		rConnection.parseAndEval("stock$V2<-as.numeric(gsub(',','',as.character(stock$V2)))");
		rConnection.parseAndEval("stock$V3<-gsub(c('▼'),'-',as.character(stock$V3))");
		rConnection.parseAndEval("stock$V3<-gsub(c('▲'),'',as.character(stock$V3))");
		rConnection.parseAndEval("stock$V3<-as.numeric(gsub(',','',as.character(stock$V3)))");
	
		rConnection.parseAndEval("stock$V4<-gsub(c('+'),'',as.character(stock$V4))");
		rConnection.parseAndEval("stock$V4<-gsub(c('%'),'',as.character(stock$V4))");
		rConnection.parseAndEval("stock$V4<-as.numeric(gsub(',','',as.character(stock$V4)))");
		
		rConnection.parseAndEval("stock$V5<-as.numeric(gsub(',','',as.character(stock$V5)))");
		rConnection.parseAndEval("stock$V6<-as.numeric(gsub(',','',as.character(stock$V6)))");
		rConnection.parseAndEval("stock$V7<-as.numeric(gsub('%','',as.character(stock$V7)))");

		rConnection.parseAndEval("colnames(stock) = c(\"종목명\", \"현재가\", \"전일비\", \"등락률\", \"시가총액\",\r\n" + 
				"                    \"상장주식수\", \"외국인비율\")");
		
		rConnection.parseAndEval("stock_order5 = arrange(stock, desc(시가총액))");
		rConnection.parseAndEval("p5<-ggplot(data=stock_order5[1:10,], aes(x=reorder(종목명, -시가총액), y=시가총액))+\r\n" + 
				"  geom_bar(stat=\"identity\", fill=\"#7777DD\")");

		rConnection.parseAndEval("stock_order2 = arrange(stock,desc(현재가))");
		rConnection.parseAndEval("p2<-ggplot(data=stock_order2[1:10,], aes(x=reorder(종목명, -현재가), y=현재가))+\r\n" + 
				"  geom_bar(stat=\"identity\", fill=\"#009E73\")");

		rConnection.parseAndEval("stock_order3.1 = arrange(stock,desc(전일비))");
		rConnection.parseAndEval("p3.1<-ggplot(data=stock_order3.1[1:10,], aes(x=reorder(종목명, -전일비), y=전일비))+\r\n" + 
				"  geom_bar(stat=\"identity\", fill=\"#0072B2\")");

		rConnection.parseAndEval("stock_order3.2 = arrange(stock,전일비)");
		rConnection.parseAndEval("p3.2<-ggplot(data=stock_order3.2[1:10,], aes(x=reorder(종목명, 전일비), y=전일비))+\r\n" + 
				"  geom_bar(stat=\"identity\", fill=\"#D55E00\")");

				
		rConnection.parseAndEval("par(mfrow=c(2,2))");
		rConnection.parseAndEval("p5");
		rConnection.parseAndEval("p2");
		rConnection.parseAndEval("p3.1");
		rConnection.parseAndEval("p3.2");

		rConnection.parseAndEval("graphics.off()");
		
		rexp = rConnection.parseAndEval("r=readBin('test2.jpg','raw',1024*1024); unlink('test2.jpg'); r");
		rConnection.close( );
		
		return rexp.asBytes( );
	}
}
