package com.example.demo.util;

public class CustomError {

	    private String errorMessage ="";
	    private boolean error = true;
	 
	    public CustomError(String errorMessage){
	        this.errorMessage = errorMessage;
	    }
	 
	    public CustomError() {
			// TODO Auto-generated constructor stub
		}

		public String getErrorMessage() {
	        return errorMessage;
	    }

		public boolean isError() {
			return error;
		}

		public void setError(boolean checkError) {
			this.error = checkError;
		}

		public void setErrorMessage(String errorMessage) {
			this.errorMessage = errorMessage;
		}
	    
	    
}
