let Singleton = (function () {

    let instance = null;
    function createInstance() {
        let ob = new Object("Hello")
        return ob;
    }

    return (
        function getInstance() {
            if (instance == null)
                instance = createInstance();
            return instance;
        }
    )
})



let instance1 = Singleton.getInstance();
let instance2 = Singleton.getInstance();

console.log(instance1 === instance2); //--> true