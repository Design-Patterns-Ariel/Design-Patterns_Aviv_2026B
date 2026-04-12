class Singleton:

    _instance = None
    def __init__(self):
        if not Singleton._instance:
            print("Creating new instance")
            Singleton.instance = self
        else:
            raise Exception(Singleton._instance)

def get_instance():
    instance=None
    try:
        instance = Singleton()
    except Exception as e:
        instance = e
    return instance
 

if __name__ == "__main__":
    s1 = get_instance()
    print(s1)
    s2 = get_instance()
    print(s2)
    Singleton._instance = None    
    s3 = get_instance()
    print(s3)