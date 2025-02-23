export interface IProductImage  {
    id: number;
    name: string;
    priority: number;
}

export interface IProduct  {
    id: number;
    creationTime: string; // або `Date`, якщо потрібно працювати з об'єктами Date
    name: string;
    images: IProductImage[];
    description: string;
    categoryName: string;
    price: string;
}