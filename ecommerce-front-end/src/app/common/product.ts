export class Product {
    constructor(public sku: string,
                public name: string,
                public price: number,
                public description: string,
                public imgUrl: string,
                public active: boolean,
                public unitInStock: number,
                public dateCreated: Date,
                public lastUpdated: Date){           
    }
}   
