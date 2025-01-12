export interface ICategory  {
    id: number;
    creationTime: string; // або `Date`, якщо потрібно працювати з об'єктами Date
    name: string;
    image: string;
    description: string;
}
