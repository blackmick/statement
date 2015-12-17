namespace java com.shawn.finance.statement.rpc

service StatementAgent{
    string query(1:i32 type, 2:string data, 3:i32 page=0, 4:i32 perPage=20)
    string balance(1:i32 type, 2:i32 idType, 3:string id, 4:string data)
}