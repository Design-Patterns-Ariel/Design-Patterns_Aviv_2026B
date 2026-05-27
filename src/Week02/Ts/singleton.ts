class Singleton {
    private constructor(){ }

    private static instance:Singleton=new Singleton();

    public static getInstance():Singleton{
        return Singleton.instance;
    }

    public hotelList:string[]=[];
}

console.log("Bob selected XY hotel");
let bobObj:Singleton=Singleton.getInstance();
bobObj.hotelList.push("XY");
console.log(bobObj.hotelList);  //--> [ 'XY' ]

console.log("Alice selected WZ hotel");
let aliceObj:Singleton=Singleton.getInstance();
aliceObj.hotelList.push("WZ");
console.log(aliceObj.hotelList); //--> [ 'XY', 'WZ' ]
