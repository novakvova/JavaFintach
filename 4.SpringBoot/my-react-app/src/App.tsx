import React from "react";
import {BrowserRouter as Router, Route, Routes, NavLink} from "react-router-dom";
import Home from "./pages/Home";
import About from "./pages/About";
import Contact from "./pages/Contact";
import CategoryCreatePage from "./pages/category/CategoryCreatePage.tsx";

const App: React.FC = () => {
    return (
        <Router>
            <div className="flex flex-col min-h-screen">
                {/* Navbar */}
                <header className="bg-blue-600 text-white p-4 shadow-md">
                    <nav className="container mx-auto flex justify-between items-center">
                        <h1 className="text-2xl font-bold">My App</h1>
                        <ul className="flex space-x-4">
                            <li>
                                <NavLink
                                    to="/"
                                    className={({isActive}) =>
                                        `hover:underline ${isActive ? "font-bold underline" : ""}`
                                    }
                                    end
                                >
                                    Home
                                </NavLink>
                            </li>
                            <li>
                                <NavLink
                                    to="/about"
                                    className={({isActive}) =>
                                        `hover:underline ${isActive ? "font-bold underline" : ""}`
                                    }
                                >
                                    About
                                </NavLink>
                            </li>
                            <li>
                                <NavLink
                                    to="/contact"
                                    className={({isActive}) =>
                                        `hover:underline ${isActive ? "font-bold underline" : ""}`
                                    }
                                >
                                    Contact
                                </NavLink>
                            </li>
                        </ul>
                    </nav>
                </header>

                {/* Main Content */}
                <main className="flex-grow container mx-auto p-4">
                    <Routes>
                        <Route path="/" element={<Home/>}/>
                        <Route path="/about" element={<About/>}/>
                        <Route path="/contact" element={<Contact/>}/>
                        <Route path="/category">
                            <Route path="create" element={<CategoryCreatePage/>}/>
                        </Route>
                    </Routes>
                </main>

                {/* Footer */}
                <footer className="bg-gray-800 text-white p-4 text-center">
                    <p>© 2025 My App. All rights reserved.</p>
                </footer>
            </div>
        </Router>
    );
};

export default App;
