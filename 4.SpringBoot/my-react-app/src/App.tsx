import './App.css'
import {Button} from "flowbite-react";
import {FaBeer} from "react-icons/fa";
import {useEffect} from "react";
import {APP_ENV} from "./env"; // Імпорт іконки "FaBeer" з FontAwesome
import axios from "axios";

function App() {

    useEffect(() => {
        axios.get(`${APP_ENV.REMOTE_BASE_URL}/api/categories`)
            .then(resp => {
                console.log("Server result", resp.data);
            });
        console.log("Is use Effect");
    }, []);

    console.log("App is running!", APP_ENV.REMOTE_BASE_URL);

    return (
        <>
            <h1 className="text-3xl font-bold underline">
                Let's have a beer! <FaBeer/>
            </h1>
            <Button>Click me</Button>;
        </>
    )
}

export default App
