<?php

class Tarea{
  
    // database connection and table name
    private $db;
    private $table_name = "tbl_tarea";
  
    // object properties
    public $id;
    public $name;
    public $description;
      
    // constructor with $db as database connection
    public function __construct($dbt){
        $this->db = $dbt;
    }

    // read products
    function read(){
        
        // select all query
        $query = "SELECT * FROM " . $this->table_name;        
        
        return $this->db->query($query);
        //return $stmt;
    }

    // create product
    function create(){
    
        // query to insert record
        $query = "INSERT INTO " . $this->table_name . "(name, description) VALUES ('$this->name', '$this->description')";
        
        // prepare query
        $stmt = $this->db->prepare($query);
    
        // execute query
        if($stmt->execute()){
            return true;
        }
    
        return false;        
    }    

    // update the product
    function update(){
    
        // update query
        $query = "UPDATE
                    " . $this->table_name . "
                SET
                    name = :name,                    
                    description = :description
                WHERE
                    id = :id";
    
        // prepare query statement
        $stmt = $this->db->prepare($query);
    
        // sanitize
        $this->name=htmlspecialchars(strip_tags($this->name));        
        $this->description=htmlspecialchars(strip_tags($this->description));
        $this->id=htmlspecialchars(strip_tags($this->id));
    
        // bind new values
        $stmt->bindParam(':name', $this->name);
        $stmt->bindParam(':description', $this->description);
        $stmt->bindParam(':id', $this->id);
    
        // execute the query
        if($stmt->execute()){
            return true;
        }    
        return false;
    }

    // delete the product
    function delete(){
    
        // delete query
        $query = "DELETE FROM " . $this->table_name . " WHERE id = ?";
    
        // prepare query
        $stmt = $this->db->prepare($query);
    
        // sanitize
        $this->id=htmlspecialchars(strip_tags($this->id));
    
        // bind id of record to delete
        $stmt->bindParam(1, $this->id);
    
        // execute query
        if($stmt->execute()){
            return true;
        }
    
        return false;
    }

    // search products
    function search($keywords){
    
        // select all query
        $query = "SELECT p.id, p.name, p.description FROM " . $this->table_name . " p          
                WHERE p.name LIKE ? OR p.description LIKE ? ";
    
        // prepare query statement
        $stmt = $this->db->prepare($query);
    
        // sanitize
        $keywords= htmlspecialchars(strip_tags($keywords));
        $keywords = "%{$keywords}%";
    
        // bind
        $stmt->bindParam(1, $keywords);
        $stmt->bindParam(2, $keywords);        
        
        // execute query
        $stmt->execute();
        
        return $stmt;
    }

    // read products with pagination
    public function readPaging($from_record_num, $records_per_page){
    
        // select query
        $query = "SELECT p.id, p.name, p.description FROM " . $this->table_name . " p
                    LIMIT ?, ?";
    
        // prepare query statement
        $stmt = $this->db->prepare( $query );
    
        // bind variable values
        $stmt->bindParam(1, $from_record_num, PDO::PARAM_INT);
        $stmt->bindParam(2, $records_per_page, PDO::PARAM_INT);
    
        // execute query
        $stmt->execute();
    
        // return values from database
        return $stmt;
    }

    // used for paging products
    public function count(){
        $query = "SELECT COUNT(*) as total_rows FROM " . $this->table_name . "";
    
        $stmt = $this->db->prepare( $query );
        $stmt->execute();
        $row = $stmt->fetch(PDO::FETCH_ASSOC);
    
        return $row['total_rows'];
    }
}
?>