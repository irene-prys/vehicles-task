vehicles-task
==============
Simple rest application<br/>
as a persistent storage uses mongodb

#### requirments
mongodb should be installed and started 

#### build project
project builds with maven <br/>
to build the project run the standard command: 
```
mvn clean install
``` 
after that the jar with the name 'vehicles-0.0.1-SNAPSHOT.jar' will be created in folder 'target'

run it with the command: 
``` 
java -jar vehicles-0.0.1-SNAPSHOT.jar
``` 

#### usage of the application
the application allows to create the vehicle, search by id, and search the vehicles in the certain areas

- to create the new vehicle:
```
post http://localhost:8080/vehicles
{
    "id": "vehicle1",
    "location": {
        "x": 10,
        "y": 3
    }
}
```

- to search by id (for instance id is 'vehicle1'):
```
http://localhost:8080/vehicles/name/vehicle1
```

- to search in some area (the area defines with 2 points: 
leftTop point and bottomRight point with the appropriate longitude and latitude)
``` 
http://localhost:8080/vehicles/rectangle/topLeftLat/0/topLeftLng/0/bottomRightLat/3/bottomRightLng/5
```




 


