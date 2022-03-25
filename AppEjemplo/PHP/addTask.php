<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
  
// get database connection
include_once 'config/database.php';
  
// instantiate tarea object
include_once 'objetos/tarea.php';
  
$database = new Database();
$db = $database->getConexion();

$tarea = new Tarea($db);
  
// get posted data
$data = new stdClass();
$data->name = $_POST['name'];
$data->description = $_POST['description'];

// make sure data is not empty
if( !empty($data->name) && !empty($data->description) ){
  
    // set tarea property values
    $tarea->name = $data->name;    
    $tarea->description = $data->description;    
  
    // create the tarea
    if($tarea->create()){
  
        // set response code - 201 created
        http_response_code(201);
  
        // tell the user
        echo json_encode(array("message" => "tarea was created."));
    }  
    // if unable to create the tarea, tell the user
    else{
  
        // set response code - 503 service unavailable
        http_response_code(503);
  
        // tell the user
        echo json_encode(array("message" => "Unable to create tarea."));
    }
}
  
// tell the user data is incomplete
else{
  
    // set response code - 400 bad request
    http_response_code(400);
  
    // tell the user
    echo json_encode(array("message" => "Unable to create tarea. Data is incomplete."));
}
?>
