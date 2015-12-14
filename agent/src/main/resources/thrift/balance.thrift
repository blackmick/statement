namespace java com.shawn.finance.statement.rpc

service Balance{
    string Balance(1:i32 type, 2:i32 idType, 3:string data)
}