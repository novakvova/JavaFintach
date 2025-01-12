import './App.css'
import { Button } from "flowbite-react";
import { FaBeer } from "react-icons/fa"; // Імпорт іконки "FaBeer" з FontAwesome

function App() {

  return (
    <>
        <h1 className="text-3xl font-bold underline">
            Let's have a beer! <FaBeer />
        </h1>
        <Button>Click me</Button>;
    </>
  )
}

export default App
