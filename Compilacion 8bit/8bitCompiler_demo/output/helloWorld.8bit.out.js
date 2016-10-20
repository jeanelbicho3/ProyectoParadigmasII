function main(){
	 JMP print_string 
ARGS : 
	 DB "Hello World!";
	 DB 0;
print_string: 
	 MOVE C, ARGS ; 
};