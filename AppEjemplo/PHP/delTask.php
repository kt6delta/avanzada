<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  
// include database and object file
include_once 'config/database.php';
include_once 'objetos/tarea.php';
  
// get database connection
$database = new Database();
$db = $database->getConexion();
  
// prepare tarea object
$tarea = new Tarea($db);
  
// get$tarea id
$data = new stdClass();
$data->id = $_POST['id'];
  
// set tarea id to be deleted
$tarea->id = $data->id;
  
// delete the tarea
if($tarea->delete()){
  
    // set response code - 200 ok
    http_response_code(200);
  
    // tell the user
    echo json_encode(array("message" => "Product was deleted."));
}
  
// if unable to delete the$tarea
else{
  
    // set response code - 503 service unavailable
    http_response_code(503);
  
    // tell the user
    echo json_encode(array("message" => "Unable to delete tarea."));
}
?>
