export default function ButtonOrderItem({page, handlePage}){
    return(
        <button onClick={() => handlePage(page)}>
            {page}
        </button>
    );
}