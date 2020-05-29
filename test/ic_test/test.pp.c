int f(int a, int b){
    return a + b;
}

int main(){
    int a = 1, b = 2, c, i=0;
    c = f(a, b) + 1;
    if(a<b){
        a = 0;
    }
    else{
        b = 0;
    }
    for(i=0; i<a ;i++){
        b = 2;
    }
    c =4;
}
