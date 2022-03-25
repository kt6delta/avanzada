<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
 

// database connection will be here
// include database and object files
include_once 'config/database.php';
include_once 'objetos/tarea.php';

// instantiate database and tarea $tarea object
$database = new Database();
$db = $database->getConexion();
if($db == null){
    echo json_encode(
        array("message" => "Error en la conexión a la BD")
    );
    die();
}
// initialize object
$tarea = new Tarea($db);

// read tarea$tareas will be here
// query tarea$tareas
$stmt = $tarea->read();

/*
//Para muchas bases de datos, PDOStatement::rowCount() no devuelve el número de filas afectadas por una sentencia SELECT. 
$num = $stmt->rowCount(); 
*/

  
    // tareas array
    $tareas_arr=array();
    $tareas_arr["records"]=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    // http://stackoverflow.com/questions/2770630/pdofetchall-vs-pdofetch-in-a-loop
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $tarea_item=array(
            "id" => $id,
            "name" => $name,
            "description" => html_entity_decode($description),            
        );
  
        array_push($tareas_arr["records"], $tarea_item);
    }
  
    $num = count($tareas_arr);

    if($num>0){
        // set response code - 200 OK
        http_response_code(200);
        // show tarea$tareas data in json format
        echo json_encode($tareas_arr);
    }else{
        // no tarea found will be here
    
        // set response code - 404 Not found
        http_response_code(404);
    
        // tell the user no tarea$tareas found
        echo json_encode(
            array("message" => "No tarea found.")
        );
    }
